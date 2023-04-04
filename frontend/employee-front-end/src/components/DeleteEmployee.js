import React, { useState } from 'react';
import "../style/DeleteEmployee.css";

function DeleteEmployee(props) {
  const [id, setId] = useState('');

  const handleSubmit = () => {
    fetch(`http://localhost:8080/delete/${id}`, {
      method: 'DELETE',
    })
      .then(response => response.json())
      .then(data => {
        props.onDelete(data);
        setId('');
      });
  };

  return (
      <>
    <div className="delete-employee-container">
      <h3>DELETE Employee</h3>
      <form onSubmit={handleSubmit}>
        <label>
          ID:
          <input type="text" value={id} onChange={e => setId(e.target.value)} />
        </label>
        <button type="submit">Delete</button>
      </form>
    </div>
    </>
  );
}

export default DeleteEmployee;
