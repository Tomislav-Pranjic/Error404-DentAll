

function Transportation(){


    return (
        <div className="home-container">
          <header>
            <nav className="navbar bg-body-tertiary">
              <div className="container-fluid">
                <a className="navbar-brand">DentAll</a>
                <a className="nav-link" href="/accommodation">
                  Accommodation
                </a>
                <a className="nav-link" href="transportation/">
                  Transportation
                </a>
                <a className="nav-link" href="/users">
                  Users
                </a>
                <a className="btn btn-dark" href="/" role="button">Logout</a>
              </div>
            </nav>
          </header>
          <main>
            <div className="main-container">

            {/* ovdje treba dodati onaj glavni dio */}


            </div>
          </main>
    
          <footer className="footer-container">
            <p className="text-center text-muted">&copy; 2023 DentAll.</p>
          </footer>
        </div>
      );
}

export default Transportation;