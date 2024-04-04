import axios from "axios"

export async function getUserById(id) {
    const response = await axios.get(`/user/${id}`);

    return response.data;
}