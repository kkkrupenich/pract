import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addCard } from "../services/cardService";


export default function AddCard() {
    const [number, setNumber] = useState('');
    const [expirationDate, setExpirationDate] = useState('');
    const [holdersName, setHoldersName] = useState('');
    const [cvv, setCvv] = useState('');

    const navigate = useNavigate();

    return (
        <section>
            <input
                placeholder="Number"
                type="text"
                onChange={(e) => setNumber(e.target.value)}
            />
            <input
                placeholder="Expiration date"
                type="date"
                onChange={(e) => setExpirationDate(e.target.value)}
            />
            <input
                placeholder="Holder's name"
                type="text"
                onChange={(e) => setHoldersName(e.target.value)}
            />
            <input
                placeholder="CVV"
                type="text"
                onChange={(e) => setCvv(e.target.value)}
            />
            <button
                onClick={addCardOnClick}>
                Add card
            </button>
        </section>
    );

    async function addCardOnClick() {
        addCard(number, expirationDate, holdersName, cvv)
        .then(cardResponse => {
            console.log(cardResponse);
        });
        navigate('/cards');
    }
}