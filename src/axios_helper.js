import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.headers.post["Content-Type"] = 'application/json'

export const getAuthToken = () => {
    return localStorage.getItem("token");
}

export const setAuthToken = (token) => {
    localStorage.setItem("token", token);
}


export const request = (method,url,data) => {
    let headers = {};
    const authToken = getAuthToken();
    if(getAuthToken() != null && getAuthToken()!= "null") { 
        headers = {"Authorization": `Token ${authToken}`};
    }
    return axios({
        method: method,
        headers: headers,
        url: url,
        data: data
    });
};