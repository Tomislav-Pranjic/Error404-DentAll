import { useState } from 'react';
import './Login.css';


interface Props{
    onLogin: (username: string, password: string) => void;
}

function Login({onLogin}: Props){
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = () => {
    onLogin(username,password);
  };

  return (
    <div className="login-container" style={{ backgroundColor: '#f0f8ff' }}>
        <form className="login-box">
            <h2 className="text-center mb-3"> Login</h2>
            <div className="mb-3">
                <label htmlFor="loginUsername" className="form-label">Username</label>
                <input type="text" className="form-control" id="loginUsername" aria-describedby="emailHelp"
                value={username} onChange={(e) => setUsername(e.target.value)} />
                <div id="emailHelp" className="form-text">Input your admin credentials </div>
            </div>
            <div className="mb-3">
                <label htmlFor="loginPassword" className="form-label">Password</label>
                <input type="password" className="form-control" id="loginPassword" 
                value={password} onChange={(e) => setPassword(e.target.value)} />
            </div>
            <button type="submit" className="btn btn-primary" onClick={handleLogin}>Submit</button>
        </form>
    </div>
  );
};

export default Login;
