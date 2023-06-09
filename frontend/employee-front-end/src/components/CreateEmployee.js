import React, { useState } from 'react';
import "../style/CreateEmployee.css";

function CreateEmployee(props) {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');

  const handleSubmit = () => {
    const employee = { name, email };
    fetch('http://localhost:8080/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(employee),
    })
      .then(response => response.json())
      .then(data => {
        props.onCreate(data);
        setName('');
        setEmail('');
      });
  };

  return (
    <>
    <div className="form-container">
      <h3>CREATE Employee</h3>
      <form onSubmit={handleSubmit}>
        <label>
          Name:
          <input type="text" value={name} onChange={e => setName(e.target.value)} />
        </label>
        <label>
          Email:
          <input type="email" value={email} onChange={e => setEmail(e.target.value)} />
        </label>
        <button type="submit">Create</button>
      </form>
    </div>
    </>
  );
}

export default CreateEmployee;
