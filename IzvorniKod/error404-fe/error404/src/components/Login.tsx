import { FormEvent, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css';

const correctLogin = [['Nikola','12345'] , ['Ivo','lozinka']];

function Login(){
  const navigate = useNavigate();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [alertVisible, setAlertVisibility] = useState(false)

  function isLoginCorrect(user: string, pass: string) {
    for (var i = 0; i < correctLogin.length; i++) {
      var pair = correctLogin[i];
      if (pair[0] === user && pair[1] === pass) {
        return true;
      }
    }
    return false;
  }

  const handleLogin = (event: FormEvent) => {

    event.preventDefault(); // da ne bi refreshalo

    if (isLoginCorrect(username,password)){
      console.log("Password is correct");
      navigate('/home'); // redirecta na home ako su podatci dobri

    } else {
      // obavijesti da je netocno
      setAlertVisibility(true)

    }
  };

  return (

    <div className="login-container" style={{ backgroundColor: '#f0f8ff' }}>
        <form className="login-box" onSubmit={handleLogin}>
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

            <button type="submit" className="btn btn-primary">Submit</button>

            {alertVisible && <div className="alert alert-danger alert-dismissible fade show" role="alert">
                Wrong login credentials
                <button type="button" className="btn-close" data-bs-dismiss="alert" aria-label="Close" onClick={() => setAlertVisibility(false)}></button>
            </div>}
        </form>
    </div>
  );
};

export default Login;
