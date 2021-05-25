import axios from "axios";

const API = 'http://localhost:8080/';

export const ADD = 'add';
export const DELETE = 'delete';
export const EDIT = 'edit';

export const TABLE_USERS = 'users';
export const TABLE_CATEGORIES = 'categories';
export const TABLE_PROJECTS = 'projects';
export const TABLE_PROGRAMMERS = 'programmers';
export const TABLE_COMMENTS = 'comments';

class DbService {
    async login(username, password) {
        return axios.post(API + 'auth', {username, password}).then(response => {
            let token = response.data
            if (token) {
                localStorage.setItem('token', JSON.stringify(token))
            }
            return response.data
        })
    }

    async logout() {
        localStorage.removeItem('token')
    }

    async register(username, password) {
        return axios.post(API + 'auth/register', {username, password})
    }

    getCurrentToken() {
        return JSON.parse(localStorage.getItem('token'))
    }

    async getAllByTable(table) {
        return axios.get(API + table)
    }

    async getItemByIdAndTable(id, table) {
        return axios.get(API + table + '/' + id)
    }

    async addBook(title, body, footer, imgPath, shortDesc) {
        return axios.post(API + 'addBook', {title, body, footer, imgPath, shortDesc})
    }

    async changeItem(action, table, item) {
        let token = JSON.parse(localStorage.getItem('token'))['jwtToken']
        if (action === ADD) {
            return axios.post(`${API}${token}/${ADD}/${table}`,item)
        }
        if (action === DELETE) {
            return axios.delete(`${API}${token}/${DELETE}/${table}`, {data: item})
        }
        if (action === EDIT) {
            return axios.put(`${API}${token}/${EDIT}/${table}`, item)
        }
    }
}

export default new DbService()
