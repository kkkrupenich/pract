import { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { updateCard } from "../services/cardService";


export default function UpdateCard() {
    const [number, setNumber] = useState('');
    const {state} = useLocation();
    const {id} = state;
    const [expirationDate, setExpirationDate] = useState('');
    const [holdersName, setHoldersName] = useState('');
    const [cvv, setCvv] = useState('');

    const navigate = useNavigate();

    return (
        <div className="form-login">
            <div className="form-group row">
                <label htmlFor="inputNumber">Card number</label>
                <input placeholder="Number" type="text" id="inputNumber" onChange={(e) => setNumber(e.target.value)} />
            </div>
            <div className="form-group row">
                <label htmlFor="inputExpiration">Expiration date</label>
                <input placeholder="Expiration date" type="date" id="inputExpiration" onChange={(e) => setExpirationDate(e.target.value)} />
            </div>
            <div className="form-group row">
                <label htmlFor="inputHolder">Holder's name</label>
                <input placeholder="Holder's name" type="text" id="inputHolder" onChange={(e) => setHoldersName(e.target.value)} />
            </div>
            <div className="form-group row">
                <label htmlFor="inputCVV">CVV</label>
                <input placeholder="CVV" type="text" id="inputCVV" onChange={(e) => setCvv(e.target.value)} />
            </div>
            <div className="button">
                <button type="button" className="btn btn-success" onClick={updateCardOnClick}>
                    Add card
                </button>
            </div>
        </div>
    );

    async function updateCardOnClick() {
        await updateCard(id, number, expirationDate, holdersName, cvv)
        navigate('/cards');
    }
}