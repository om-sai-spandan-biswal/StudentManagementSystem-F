// src/api/api.js
import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080",
});

const unwrap = (res) => res.data.data;

const handleError = (err) => {
    if (err.response?.data?.error) {
        throw new Error(err.response.data.error.message);
    }
    throw new Error("Network Error");
};

export const StudentAPI = {
    getAll: () => api.get("/students").then(unwrap).catch(handleError),
    getOne: (id) => api.get(`/students/${id}`).then(unwrap).catch(handleError),
    getCourses: (id) =>
        api.get(`/students/${id}/course`).then(unwrap).catch(handleError),
    create: (data) => api.post("/students", data).then(unwrap).catch(handleError),
    update: (id, data) =>
        api.put(`/students/${id}`, data).then(unwrap).catch(handleError),
    delete: (id) =>
        api.delete(`/students/${id}`).then(unwrap).catch(handleError),
    bulkUpload: (formData) =>
        api.post("/students/bulk", formData).then(unwrap).catch(handleError),
};

export const CourseAPI = {
    getAll: () => api.get("/courses").then(unwrap).catch(handleError),
    getOne: (id) => api.get(`/courses/${id}`).then(unwrap).catch(handleError),
    getStudents: (id) =>
        api.get(`/courses/${id}/course`).then(unwrap).catch(handleError),
    create: (data) => api.post("/courses", data).then(unwrap).catch(handleError),
    update: (id, data) =>
        api.put(`/courses/${id}`, data).then(unwrap).catch(handleError),
    delete: (id) =>
        api.delete(`/courses/${id}`).then(unwrap).catch(handleError),
};

export const EnrollmentAPI = {
    enroll: (data) =>
        api.post("/enrollment", data).then(unwrap).catch(handleError),
};