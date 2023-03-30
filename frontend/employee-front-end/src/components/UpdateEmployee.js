import React, { useState } from 'react';

function UpdateEmployee() {
  const [id, setId] = useState('');
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    const employee = { name, email };
    fetch(`http://localhost:8080/update/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(employee),
    })
      .then(response => response.json())
      .then(data => {
        console.log(data);
        setId('');
        setName('');
        setEmail('');
      });
  };

  return (
    <>
    <h3>UPDATE Employee</h3>
    <form onSubmit={handleSubmit}>
      <label>
        ID:
        <input type="text" value={id} onChange={e => setId(e.target.value)} />
      </label>
      <label>
        Name:
        <input type="text" value={name} onChange={e => setName(e.target.value)} />
      </label>
      <label>
        Email:
        <input type="email" value={email} onChange={e => setEmail(e.target.value)} />
      </label>
      <button type="submit">Update</button>
    </form>
    </>
  );
}

export default UpdateEmployee;
