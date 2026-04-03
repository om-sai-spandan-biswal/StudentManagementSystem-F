import { useEffect, useState } from "react";
import { StudentAPI } from "../api/api";
import { useToast } from "../context/ToastContext";
import Loader from "../components/Loader";
import { Link } from "react-router-dom";

export default function Students() {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const { show } = useToast();

    const load = async () => {
        try {
            setLoading(true);
            const res = await StudentAPI.getAll();
            setData(res);
        } catch (e) {
            show(e.message);
        } finally {
            setLoading(false);
        }
    };

    const del = async (id) => {
        try {
            await StudentAPI.delete(id);
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
            <Link to="/student/new" className="bg-blue-500 text-white p-2">
                Add Student
            </Link>

            {data.map((s) => (
                <div key={s.id} className="border p-3 mt-2 flex justify-between">
                    <Link to={`/student/${s.id}`}>{s.name}</Link>
                    <div className="flex gap-2">
                        <Link to={`/student/edit/${s.id}`}>Edit</Link>
                        <button onClick={() => del(s.id)}>Delete</button>
                    </div>
                </div>
            ))}
        </div>
    );
}