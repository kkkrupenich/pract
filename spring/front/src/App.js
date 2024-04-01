import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  const [roles, setRoles] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);

    fetch('roles')
      .then(response => response.json())
      .then(data => {
        setRoles(data);
        setLoading(false);
      })
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to .
        </p>
        <div className="App-intro">
          <h2>Roles List</h2>
          {roles.map(role =>
            <div key={role.id}>
              {role.roleName}
            </div>
          )}
        </div>
      </header>
    </div>
  );
}

export default App;
