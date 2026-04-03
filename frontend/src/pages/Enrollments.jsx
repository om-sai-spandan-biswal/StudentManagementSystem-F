import { useEffect, useState } from "react";
import { StudentAPI, CourseAPI, EnrollmentAPI } from "../api/api";
import { useToast } from "../context/ToastContext";

export default function Enrollments() {
    const [students, setStudents] = useState([]);
    const [courses, setCourses] = useState([]);
    const [data, setData] = useState({});
    const { show } = useToast();

    useEffect(() => {
        StudentAPI.getAll().then(setStudents);
        CourseAPI.getAll().then(setCourses);
    }, []);

    const enroll = async () => {
        try {
            await EnrollmentAPI.enroll(data);
            show("Enrollment success");
        } catch (e) {
            show(e.message);
        }
    };

    return (
        <div>
            <select onChange={(e) => setData({ ...data, studentId: e.target.value })}>
                <option>Select Student</option>
                {students.map((s) => (
                    <option value={s.id}>{s.name}</option>
                ))}
            </select>

            <select onChange={(e) => setData({ ...data, courseId: e.target.value })}>
                <option>Select Course</option>
                {courses.map((c) => (
                    <option value={c.id}>{c.name}</option>
                ))}
            </select>

            <button onClick={enroll}>Enroll</button>
        </div>
    );
}