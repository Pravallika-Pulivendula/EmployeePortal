import React, { useEffect, useState } from "react";
import * as employeeService from "../services/EmployeeService";
import { Link, Navigate, useNavigate, useParams } from "react-router-dom";

const AddEmployee = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [everestEmailId, setEverestEmailId] = useState("");
  const [password, setPassword] = useState("");
  const { empId } = useParams();

  const saveEmployee = (e) => {
    e.preventDefault();

    const employee = { firstName, lastName, everestEmailId, password, empId };
    if (empId) {
      employeeService
        .updateEmployee(empId, employee)
        .then((response) => {
          console.log("Employee data updated successfully", response.data);
        })
        .catch((error) => {
          console.log("Something went wrong", error);
        });
    } else {
      employeeService
        .createEmployee(employee)
        .then((response) => {
          console.log("employee added successfully", response.data);
        })
        .catch((error) => {
          console.log("something went wroing", error);
        });
    }
  };

  useEffect(() => {
    if (empId) {
      employeeService
        .getEmployeeById(empId)
        .then((employee) => {
          setFirstName(employee.data.firstName);
          setLastName(employee.data.lastName);
          setEverestEmailId(employee.data.everestEmailId);
          setPassword(employee.data.password);
        })
        .catch((error) => {
          console.log("Something went wrong", error);
        });
    }
  }, []);
  return (
    <div className="container">
      <div id={"saveEmployee"} className="mt-3">
        <h2 className="text-center">Save Employee</h2>
        <form className="row justify-content-center">
          <div className="form-group col-md-10 mt-2">
            <label htmlFor="firstName">FirstName</label>
            <input
              id="firstName"
              placeholder={"Enter the firstName"}
              className="form-control col-md-12"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
            />
          </div>
          <div className="form-group col-md-10 mt-2">
            <label htmlFor="lastName">LastName</label>
            <input
              id="lastName"
              placeholder={"Enter the lastName"}
              className="form-control col-md-12"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
            />
          </div>
          <div className="form-group col-md-10 mt-2">
            <label htmlFor="email">Email</label>
            <input
              id="email"
              placeholder={"Enter the email"}
              className="form-control col-md-12"
              type="text"
              value={everestEmailId}
              onChange={(e) => setEverestEmailId(e.target.value)}
            />
          </div>
          <div className="form-group col-md-10 mt-2">
            <label htmlFor="password">Password</label>
            <input
              id="password"
              className="form-control col-md-12"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <div className="form-group col-md-10 mt-2">
            <button
              type="submit"
              className="btn btn-primary"
              onClick={(e) => saveEmployee(e)}
            >
              Save
            </button>
          </div>
          <Link to="/" className="form-group col-md-10 mt-3">
            Back to Employee List
          </Link>
        </form>
      </div>
    </div>
  );
};

export default AddEmployee;
