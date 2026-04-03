import { useState } from "react";
import { StudentAPI } from "../api/api";
import { useToast } from "../context/ToastContext";

export default function BulkUpload() {
    const [file, setFile] = useState(null);
    const { show } = useToast();

    const upload = async () => {
        try {
            if (!file) {
                show("Select a file first");
                return;
            }

            const formData = new FormData();
            formData.append("file", file);

            await StudentAPI.bulkUpload(formData);
            show("Upload successful");
        } catch (e) {
            show(e.message);
        }
    };

    return (
        <div>
            <h1>Bulk Upload Students</h1>

            <input type="file" onChange={(e) => setFile(e.target.files[0])} />

            <button
                onClick={upload}
                className="bg-green-500 text-white p-2 mt-2"
            >
                Upload
            </button>
        </div>
    );
}