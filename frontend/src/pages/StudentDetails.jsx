import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { StudentAPI } from "../api/api";

export default function StudentDetails() {
    const { id } = useParams();
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        StudentAPI.getCourses(id).then(setCourses);
    }, []);

    return (
        <div>
            <h1>Enrolled Courses</h1>
            {courses.map((c) => (
                <div key={c.id}>{c.name}</div>
            ))}
        </div>
    );
}