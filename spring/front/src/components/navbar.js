import { Link } from "react-router-dom";

export default function Navbar({ isAuthenticated }) {
    return (
        <nav>
            <ul>
                { isAuthenticated ? (
                    <>
                        <li>
                            <Link to={`/cards`}>Карты</Link>
                        </li>
                        <li>
                            <Link to={`/profile`}>Профиль</Link>
                        </li>
                        <li>
                            <Link to={`/games`}>Игровые автоматы</Link>
                        </li>
                    </>
                ) : (
                    <>
                        <li>
                            <Link to={`/signup`}>Регистрация</Link>
                        </li>
                        <li>
                            <Link to={`/signin`}>Авторизация</Link>
                        </li>
                    </>
                )}
            </ul>
        </nav>
    );
}