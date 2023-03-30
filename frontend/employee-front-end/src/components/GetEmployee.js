import React, { useState, useEffect } from 'react';

function EmployeeList() {
    const [employees, setEmployees] = useState([]);
  
    useEffect(() => {
      fetch('http://localhost:8080/employees')
        .then(response => response.json())
        .then(data => setEmployees(data));
    }, []);
  
    return (
      <div>
        {employees.map(employee => (
          <div key={employee.id}>
            <h2>{employee.name}</h2>
            <p>{employee.email}</p>
          </div>
        ))}
      </div>
    );
  }

  export default EmployeeList;