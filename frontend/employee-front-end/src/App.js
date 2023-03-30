import React from 'react';
import EmployeeList from './components/GetEmployee';
import CreateEmployee from './components/CreateEmployee';
import UpdateEmployee from './components/UpdateEmployee';
import DeleteEmployee from './components/DeleteEmployee';
import './App.css';


function App() {

  return (
    <div className="App">
      <h1>
      Employee Information App
      </h1>
      < EmployeeList />
      < CreateEmployee />
      < UpdateEmployee />
      < DeleteEmployee />
    </div>
  );
}

export default App;
