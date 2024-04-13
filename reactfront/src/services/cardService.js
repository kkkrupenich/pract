import axios from "axios"

export async function addCard(number, expirationDate, holdersName, cvv) {
    let response = await axios.post(
        "/addcard",
        {
            "number": number,
            "expirationDate": expirationDate,
            "holdersName": holdersName,
            "cvv": cvv
        }
    )
    return response.data
}

export async function updateCard(id, number, expirationDate, holdersName, cvv) {
    let response = await axios.put(
        "/updatecard/" + id,
        {
            "number": number,
            "expirationDate": expirationDate,
            "holdersName": holdersName,
            "cvv": cvv
        }
    )
    return response.data
}

export async function deleteCard(id) {
    let response = await axios.delete(`/deletecard/${id}`)
    console.log(response.data);
    return response.data
}