import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { CourseAPI } from "../api/api";

export default function CourseDetails() {
    const { id } = useParams();
    const [students, setStudents] = useState([]);

    useEffect(() => {
        CourseAPI.getStudents(id).then(setStudents);
    }, [id]);

    return (
        <div>
            <h1 className="text-lg font-bold">Enrolled Students</h1>

            {students.map((s) => (
                <div key={s.id} className="border p-2 mt-2">
                    {s.name}
                </div>
            ))}
        </div>
    );
}