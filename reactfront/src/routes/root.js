import { useContext } from "react";
import { Outlet } from "react-router-dom";
import Navbar from "../components/navbar";
import { AppContext } from "../contexts/contexts";

export default function Root() {
    const appContext = useContext(AppContext);

    return (
        <main>
            <Navbar isAuthenticated={appContext.isAuthenticated} />
            <Outlet />
        </main>
    );
}