import { useState } from "react";
import { ToastContext } from "./ToastContext";

export default function ToastProvider({ children }) {
    const [msg, setMsg] = useState("");

    const show = (m) => {
        setMsg(m);
        setTimeout(() => setMsg(""), 3000);
    };

    return (
        <ToastContext.Provider value={{ show }}>
            {msg && (
                <div className="fixed top-4 right-4 bg-black text-white px-4 py-2 rounded shadow">
                    {msg}
                </div>
            )}
            {children}
        </ToastContext.Provider>
    );
}