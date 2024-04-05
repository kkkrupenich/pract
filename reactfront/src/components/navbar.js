export default function Navbar({ isAuthenticated }) {
    return (
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
            <div className="container">
                <a className="navbar-brand" href="/">Home</a>
                <div className="navbar-nav">
                    {isAuthenticated ? (
                        <>
                            <a className="nav-link active" aria-current="page" href="/cards">Cards</a>
                            <a className="nav-link active" href="/profile">Profile</a>
                        </>
                    ) : (
                        <>
                            <a className="nav-link active" aria-current="page" href="/signup">Register</a>
                            <a className="nav-link active" href="/signin">Login</a>
                        </>
                    )}
                </div>
            </div>
        </nav>
    );
}