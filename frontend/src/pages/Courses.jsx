import { useEffect, useState } from "react";
import { CourseAPI } from "../api/api";
import { Link } from "react-router-dom";
import Loader from "../components/Loader";
import { useToast } from "../context/ToastContext";

export default function Courses() {
    const [courses, setCourses] = useState([]);
    const [loading, setLoading] = useState(true);
    const { show } = useToast();

    const load = async () => {
        try {
            setLoading(true);
            const data = await CourseAPI.getAll();
            setCourses(data);
        } catch (e) {
            show(e.message);
        } finally {
            setLoading(false);
        }
    };

    const del = async (id) => {
        try {
            await CourseAPI.delete(id);
            show("Deleted");
            load();
        } catch (e) {
            show(e.message);
        }
    };

    useEffect(() => {
        load();
    }, []);

    if (loading) return <Loader />;

    return (
        <div>
            <Link to="/course/new" className="bg-blue-500 text-white p-2">
                Add Course
            </Link>

            {courses.map((c) => (
                <div key={c.id} className="border p-3 mt-2 flex justify-between">
                    <Link to={`/course/${c.id}`}>{c.name}</Link>
                    <div className="flex gap-2">
                        <Link to={`/course/edit/${c.id}`}>Edit</Link>
                        <button onClick={() => del(c.id)}>Delete</button>
                    </div>
                </div>
            ))}
        </div>
    );
}