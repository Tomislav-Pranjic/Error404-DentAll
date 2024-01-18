import { FormEvent, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Base64 } from 'js-base64';
import './Login.css';


function Login(){
  const navigate = useNavigate();

  const [alertVisible, setAlertVisibility] = useState(false)
  const [loginForm, setLoginForm] = useState({ username: '', password: ''});


  function onChange(event: React.ChangeEvent<HTMLInputElement>) {
    const {name, value} = event.target;
    setLoginForm(oldForm => ({...oldForm, [name]: value}))
  }


  const handleLogin = (event: FormEvent) => {

    event.preventDefault(); // da ne bi refreshalo


    const body = `username=${loginForm.username}&password=${loginForm.password}`;
    const options = {
      method: 'POST',
      headers: new Headers({
          "Authorization": `xBasic ${Base64.encode(`${loginForm.username}:${loginForm.password}`)}`,
          "Content-Type": "application/x-www-form-urlencoded",
            "Accept": "*/*",
          "Accept-Encoding": "gzip, deflate, br",
          "Connection": "keep-alive"
      }),
      body: body
    };

    fetch('/api/login/checkCredentials', options)
      .then(response => {
        if (response.status === 200) {

          localStorage.setItem('username', loginForm.username);
          localStorage.setItem('password', loginForm.password);
          navigate('/home');
        } else {
          setAlertVisibility(true)
        }

      });

  };
  

  return (

    <div className="login-container" style={{ backgroundColor: '#f0f8ff' }}>

        <form className="login-box" onSubmit={handleLogin}>
            <h2 className="text-center mb-3"> Login</h2>

            <div className="mb-3">
                <label htmlFor="loginUsername" className="form-label">Username</label>
                <input type="text" className="form-control" id="loginUsername" aria-describedby="emailHelp"
                name="username" onChange={onChange} value={loginForm.username}/>
                <div id="emailHelp" className="form-text">Input your admin credentials </div>
            </div>

            <div className="mb-3">
                <label htmlFor="loginPassword" className="form-label">Password</label>
                <input type="password" className="form-control" id="loginPassword" 
                name="password" onChange={onChange} value={loginForm.password} />
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
