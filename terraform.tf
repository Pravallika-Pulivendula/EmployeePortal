provider "aws" {
  region     = "us-east-1"
  access_key = "AKIAR5WCDNNTZVNCTIU3"
  secret_key = "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"
}

variable "key_path" {
  default = "~/.ssh/github-actions.pub"
}

resource "aws_key_pair" "spring-app-1" {
  key_name   = "spring-app-1"
  public_key = file("${var.key_path}")
}

resource "aws_instance" "instance" {
  ami                         = "ami-09d56f8956ab235b3"
  instance_type               = "t2.micro"
  key_name                    = aws_key_pair.spring-app-1.id
  associate_public_ip_address = true
}

output "instance-private-ip" {
value = aws_instance.instance.private_ip
}

output "instance-public-ip" {
value = aws_instance.instance.public_ip
}