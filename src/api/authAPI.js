import axios from '../utils/axios';

const authApi = {
  registerUser: (data) => {
    const url = '/Auth/registerUser';
    return axios.post(url, data);
  },
  updateUser: (params, data) => {
    const url = `/Auth/${params}`;
    return axios.put(url, data);
  },
  getAll: (params) => {
    const url = '/Auth/getAll';
    return axios.get(url, { params });
  },
  getOne: (params) => {
    const url = `/Auth/getOne/${params}`;
    return axios.get(url);
  },
  delete: (params) => {
    const url = `/Auth/${params}`;
    return axios.delete(url);
  },
};
export default authApi;
