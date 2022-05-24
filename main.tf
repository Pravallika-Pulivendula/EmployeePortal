provider "aws" {
  region     = "us-east-1"
  access_key = "AKIAR5WCDNNTX3267G3R"
  secret_key = "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"
}

resource "aws_instance" "ssh-deploy" {
  ami           = "ami-0c4f7023847b90238"
  instance_type = "t2.micro"
  tags          = {
    Name = "ssh-deploy"
  }
  key_name               = "ssh-key"
  vpc_security_group_ids = [aws_security_group.ubuntu.id]

  connection {
    type        = "ssh"
    host        = self.public_ip
    port        = 22
    user        = "ubuntu"
    private_key = file("ssh-key")
    timeout     = "4m"
    agent       = true
  }

  provisioner "file" {
    source      = "docker-compose.yml"
    destination = "/home/ubuntu/docker-compose.yml"
  }

  provisioner "remote-exec" {
    inline = [
      "sudo apt-get update",
      "sudo apt-get install -y docker.io",
      "sudo apt-get install -y docker-compose",
    ]
  }
}

resource "aws_security_group" "ubuntu" {
  egress = [
    {
      cidr_blocks      = ["0.0.0.0/0",]
      description      = ""
      from_port        = 0
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      protocol         = "-1"
      security_groups  = []
      self             = false
      to_port          = 0
    }
  ]
  ingress = [
    {
      cidr_blocks      = ["0.0.0.0/0",]
      description      = ""
      from_port        = 22
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      protocol         = "tcp"
      security_groups  = []
      self             = false
      to_port          = 22
    },

    {
      cidr_blocks      = ["0.0.0.0/0",]
      description      = ""
      from_port        = 8080
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      protocol         = "tcp"
      security_groups  = []
      self             = false
      to_port          = 8080
    },

    {
      cidr_blocks      = ["0.0.0.0/0",]
      description      = ""
      from_port        = 3000
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      protocol         = "tcp"
      security_groups  = []
      self             = false
      to_port          = 3000
    },

    {
      cidr_blocks      = ["0.0.0.0/0",]
      description      = ""
      from_port        = 80
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      protocol         = "tcp"
      security_groups  = []
      self             = false
      to_port          = 80
    },

    {
      cidr_blocks      = ["0.0.0.0/0",]
      description      = ""
      from_port        = 443
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      protocol         = "tcp"
      security_groups  = []
      self             = false
      to_port          = 443
    }
  ]
}

resource "aws_key_pair" "ubuntu" {
  key_name   = "ssh-key"
  public_key = file("ssh-key.pub")
}

resource "aws_eip" "ubuntu" {
  vpc      = true
  instance = aws_instance.ssh-deploy.id
}

resource "local_file" "public_ip" {
  content = aws_instance.ssh-deploy.public_ip
  filename = ".env"
}

