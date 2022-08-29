import React, {useState} from 'react';
import "./login.scss";
import {useForm} from "react-hook-form";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {toast} from "react-toastify";


function Login(props) {


    const [showPassword, setShowPassword] = useState(false);


    const [token, setToken] = useState("");




    const navigate = useNavigate()

    const {
        handleSubmit,
        reset,
        register
    } = useForm();



    function submitForm(data) {
        axios({
            url: "http://localhost:8080/api/login",
            method: "post",
            data
        }).then(res => {
            if (res.data.success === true) {
                navigate("/admin/stores")
                localStorage.setItem("token", res.data.token)
                setToken(res.data.token)
            } else {
                toast.error("Parol noto'g'ri")
            }
        })
    }

    return (
        <div className={"login"}>

            <form onSubmit={handleSubmit(submitForm)}>

                <h1>Login</h1>

                <input
                    type="text"
                    className={"form-control"}
                    placeholder={"username"}
                    {...register("username")}
                />

                <div className={"password_input"}>
                    <input
                        type={showPassword ? "text" : "password"}
                        className={"form-control"}
                        placeholder={"password"}
                        {...register("password")}
                    />
                    {!showPassword ?
                        <i
                            onClick={() => setShowPassword(true)}
                            className={`fa-solid fa-eye ${!showPassword ? "text-success" : ""}`}
                        />
                        : <i
                            onClick={() => setShowPassword(false)}
                            className={`fa-solid fa-eye-slash ${showPassword ? "text-danger" : ""}`}
                        />
                    }
                </div>


                <button className={"btn btn-success"}>submit</button>

            </form>

        </div>
    );
}

export default Login;