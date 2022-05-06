import axios from "axios";

const API_BASE_URL = "http://localhost:8080";

export function getAllEmployees() {
  return axios.get(`${API_BASE_URL}/api/employees`);
}

export function getEmployeeById(empId) {
  return axios.get(`${API_BASE_URL}/api/employees/${empId}`);
}

export function createEmployee(employee) {
  return axios.post(`${API_BASE_URL}/api/employees`, employee);
}

export function deleteEmployee(empId) {
  return axios.delete(`${API_BASE_URL}/api/employees/${empId}`);
}

export function updateEmployee(empId, employee) {
  return axios.put(`${API_BASE_URL}/api/employees/${empId}`, employee);
}

export function searchByName(name) {
  return axios.get(`${API_BASE_URL}/api/employees/search?query=${name}`);
}
