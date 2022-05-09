import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import * as employeeService from "../services/EmployeeService";

const EmployeeList = () => {
  const [employees, setEmployees] = useState([]);
  const [keyword, setKeyword] = useState("");

  const getAllEmployees = () => {
    employeeService.getAllEmployees().then((response) => {
      console.log(response.data.data);
      setEmployees(response.data.data);
    });
  };

  const deleteEmployee = (empId) => {
    employeeService
      .deleteEmployee(empId)
      .then((response) => {
        console.log("employee deleted successfully", response);
        getAllEmployees();
      })
      .catch((e) => console.log("error", e));
  };

  useEffect(() => {
    getAllEmployees();
  }, []);

  const searchByName = () => {
    employeeService
      .searchByName(keyword)
      .then((response) => {
        setEmployees(response.data.data);
        console.log(response.data.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  return (
    <div className="mt-3 me-5 ms-5">
      <h1 className="text-center mb-5">Employee Portal</h1>
      <div className="input-group mb-3">
        <input
          type="text"
          className="form-control me-3"
          placeholder="Search by name"
          value={keyword}
          onChange={(e) => setKeyword(e.target.value)}
        />
        <div className="input-group-append">
          <button
            className="btn btn-secondary"
            type="button"
            onClick={searchByName}
          >
            Search
          </button>
        </div>
      </div>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Delete</th>
            <th>Update</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((employee) => (
            <tr key={employee.empId}>
              <td>{employee.empId}</td>
              <td>
                {employee.firstName} {employee.lastName}
              </td>
              <td>{employee.everestEmailId}</td>
              <td>
                <button
                  className={"btn btn-warning"}
                  onClick={() => deleteEmployee(employee.empId)}
                >
                  Delete
                </button>
              </td>
              <td>
                <Link
                  className="btn btn-success"
                  to={`/update/${employee.empId}`}
                >
                  Update
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/add" className="btn btn-primary mt-2">
        Add Employee
      </Link>
    </div>
  );
};

export default EmployeeList;
