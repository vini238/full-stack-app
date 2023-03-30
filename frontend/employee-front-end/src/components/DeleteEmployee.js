import React, { useState } from 'react';

function DeleteEmployee(props) {
  const [id, setId] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
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
    <h3>DELETE Employee</h3>
    <form onSubmit={handleSubmit}>
      <label>
        Id:
        <input type="text" value={id} onChange={e => setId(e.target.value)} />
      </label>
      <button type="submit">Delete</button>
    </form>
    </>
  );
}

export default DeleteEmployee;
