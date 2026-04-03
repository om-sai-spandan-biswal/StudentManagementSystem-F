import { useEffect, useState } from "react";
import { StudentAPI } from "../api/api";
import { useNavigate, useParams } from "react-router-dom";
import { useToast } from "../context/ToastContext";

export default function StudentForm() {
    const { id } = useParams();
    const nav = useNavigate();
    const { show } = useToast();

    const [form, setForm] = useState({
        name: "",
        email: "",
        contactNumber: "",
        dateOfBirth: "",
        department: "",
    });

    useEffect(() => {
        if (id) {
            StudentAPI.getOne(id).then(setForm);
        }
    }, [id]);

    const submit = async () => {
        try {
            if (id) await StudentAPI.update(id, form);
            else await StudentAPI.create(form);

            show("Saved!");
            nav("/");
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
                    onChange={(e) => setForm({ ...form, [k]: e.target.value })}
                    className="block border m-2 p-2"
                />
            ))}
            <button onClick={submit}>Save</button>
        </div>
    );
}