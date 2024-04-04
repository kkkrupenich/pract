import axios from "axios"

export async function signIn(email, password) {
    let response = await axios.post(
        "http://localhost:8080/signin",
        {
            "email": email,
            "password": password
        }
    )
    return response.data
}

export async function signUp(email, password, fio, serialNumber, identificationNumber, registration, issueDate, expirationDate) {
    let response = await axios.post(
        "http://localhost:8080/signup",
        {
            "email": email,
            "password": password,
            "fio": fio,
            "serialNumber": serialNumber,
            "identificationNumber": identificationNumber,
            "registration": registration,
            "issueDate": issueDate,
            "expirationDate": expirationDate
        }
    )
    return response.data
}