import axios from "axios"

export async function getUserById(id) {
    const response = await axios.get(`/user/${id}`);

    return response.data;
}

export async function updatePassword(password) {
    let response = await axios.post(
        "/updateuser",
        {
            "password": password
        }
    )
    return response.data
}