import React, { useState, useEffect } from 'react';
import "../style/GetEmployee.css";

function EmployeeList(props) {
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/employees')
      .then(response => response.json())
      .then(data => setEmployees(data));
  }, [props.shouldUpdateList]);

  return (
    <table className="employee-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Email</th>
        </tr>
      </thead>
      <tbody>
        {employees.map(employee => (
          <tr key={employee.id}>
            <td>{employee.id}</td>
            <td>{employee.name}</td>
            <td>{employee.email}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default EmployeeList;
