import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.headers.post["Content-Type"] = 'application/json'

export const getDataFromLocal = (field) => {
    const data = localStorage.getItem(field);
  
    if (data) {
      try {
        return JSON.parse(data);
      } catch (error) {
        console.error("Error parsing JSON:", error);
        return null; // or handle the error in an appropriate way
      }
    }
  
    return null; // or handle the empty data case in an appropriate way
  };

export const setDataToLocal = (field, data) => {
    localStorage.setItem([field], JSON.stringify(data));
}


export const request = (method,url,data) => {
    let headers = {};
    const authToken = getDataFromLocal("token");
    if(getDataFromLocal("token") != null && getDataFromLocal("token") != "null") { 
        headers = {"Authorization": `Token ${authToken}`};
    }
    return axios({
        method: method,
        headers: headers,
        url: url,
        data: data
    });
};