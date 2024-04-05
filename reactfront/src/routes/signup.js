import { useContext, useState } from "react";
import { signUp } from "../services/authService"
import { AppContext } from "../contexts/contexts";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useCookies } from "react-cookie";


export default function Signin() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [fio, setFio] = useState('');
    const [serialNumber, setSerialNumber] = useState('');
    const [identificationNumber, setIdentificationNumber] = useState('');
    const [registration, setRegistration] = useState('');
    const [issueDate, setIssueDate] = useState('');
    const [expirationDate, setExpirationDate] = useState('');
    const [cookies] = useCookies(['app']);

    const appContext = useContext(AppContext);

    const navigate = useNavigate();

    return (
        <div className="form-register">
            <div className="customRow">
                <div className="customCol">
                    <div className="form-group row">
                        <label htmlFor="inputEmail">Email</label>
                        <input placeholder="Email" type="email" id="inputEmail" onChange={(e) => setEmail(e.target.value)} />
                    </div>
                    <div className="form-group row">
                        <label htmlFor="inputPassword">Password</label>
                        <input placeholder="Password" type="password" id="inputPassword" onChange={(e) => setPassword(e.target.value)} />
                    </div>
                    <div className="form-group row">
                        <label htmlFor="inputFio">FIO</label>
                        <input placeholder="FIO" type="text" id="inputFio" onChange={(e) => setFio(e.target.value)} />
                    </div>
                    <div className="form-group row">
                        <label htmlFor="inputSerial">Serial number</label>
                        <input placeholder="Serial number" type="text" id="inputSerial" onChange={(e) => setSerialNumber(e.target.value)} />
                    </div>
                </div>
                <div className="customCol">
                    <div className="form-group row">
                        <label htmlFor="inputIdentification">Identifiaction number</label>
                        <input placeholder="Identifiaction number" type="text" id="inputIdentification" onChange={(e) => setIdentificationNumber(e.target.value)} />
                    </div>
                    <div className="form-group row">
                        <label htmlFor="inputRegistration">Registration</label>
                        <input placeholder="Registration" type="text" id="inputRegistration" onChange={(e) => setRegistration(e.target.value)} />
                    </div>
                    <div className="form-group row">
                        <label htmlFor="inputIssue">Issue date</label>
                        <input placeholder="Issue date" type="date" id="inputIssue" onChange={(e) => setIssueDate(e.target.value)} />
                    </div>
                    <div className="form-group row">
                        <label htmlFor="inputExp">Expiration date</label>
                        <input placeholder="Expiration date" type="date" id="inputExp" onChange={(e) => setExpirationDate(e.target.value)} />
                    </div>
                </div>
            </div>



            <div className="button">
                <button className="btn btn-primary" onClick={signUpClick}>Register</button>
            </div>
        </div>
    );

    async function signUpClick() {
        const data = await signUp(email, password, fio, serialNumber, identificationNumber, registration, issueDate, expirationDate);
        appContext.setCookie('userId', data.id);
        appContext.setCookie('token', data.token);
        appContext.setIsAuthenticated(true);
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + cookies.token;
        navigate('/');
    }
}