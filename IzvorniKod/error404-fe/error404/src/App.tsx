import Login from './components/Login';

function App() {

  const handleLogin = (username: string, password: string) => {

    const correctLogin = [['Nikola','12345'] , ['Ivo','lozinka']]; // ovo je testno da mozemo otic na glavnu stranicu

    function isLoginCorrect(user: string, pass: string) {
      for (var i = 0; i < correctLogin.length; i++) {
        var pair = correctLogin[i];
        if (pair[0] === user && pair[1] === pass) {
          return true; // Found a match
        }
      }
      return false; // No match found
    }

    if (isLoginCorrect(username,password)){
      console.log("Password is correct")
      console.log(username)
      console.log(password)

      // ovo bi ga trebalo redirectat na main stranicu

    } else {
      console.log("Try again")
      // ovo treba izbacit alert da je netocno
    }

    // implementiraj pravu provjeru logina

  }


  return (
    <div className="App">
      <Login onLogin={handleLogin} />
    </div>
  );
}

export default App;