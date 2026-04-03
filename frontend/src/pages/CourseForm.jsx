import { useEffect, useState } from "react";
import { CourseAPI } from "../api/api";
import { useNavigate, useParams } from "react-router-dom";
import { useToast } from "../context/ToastContext";

export default function CourseForm() {
    const { id } = useParams();
    const nav = useNavigate();
    const { show } = useToast();

    const [form, setForm] = useState({
        name: "",
        courseCode: "",
        credit: "",
        department: "",
    });

    useEffect(() => {
        if (id) {
            CourseAPI.getOne(id).then(setForm);
        }
    }, [id]);

    const submit = async () => {
        try {
            if (id) await CourseAPI.update(id, form);
            else await CourseAPI.create(form);

            show("Saved!");
            nav("/courses");
        } catch (e) {
            show(e.message);
        }
    };

    return (
        <div>
            {Object.keys(form).map((k) => (
                <input
                    key={k}
                    value={form[k]}
                    placeholder={k}
                    onChange={(e) => setForm({ ...form, [k]: e.target.value })}
                    className="block border m-2 p-2"
                />
            ))}
            <button onClick={submit}>Save</button>
        </div>
    );
}