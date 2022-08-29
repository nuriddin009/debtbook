import React, {useEffect, useState} from 'react';
import "./main.scss";
import {useForm} from "react-hook-form";
import axios from "axios";
import {toast} from "react-toastify";

function Settings(props) {

    const {
        handleSubmit,
        register
    } = useForm();

    const [day, setDay] = useState("");

    useEffect(()=>{
        getNote()
    },[])


    function submitForm(data) {
        axios({
            url: "http://localhost:8085/api/debtors/sendNotification",
            method: "put",
            data
        }).then(res => {
            toast.success("O'zgartirildi")
        })
    }

    function getNote(){
        axios({
            url:"http://localhost:8085/api/debtors/getNote",
            method:"get"
        }).then(res=>{
            setDay(res.data.dayCount)
        })
    }

    function handleSelect(e) {
        if (window.confirm("Rostdan ham o'zgartirmoqchimisiz")) {
            setDay(e.target.value)
        }
    }

    return (
        <div className={"settings"}>
            <h1 className={"text-center m-3"}>Settings</h1>
            <h4 className={"text-center"}>Qarzdor qarz olgandan keyin har
                qancha vaqtda ularga ogohlantirish borsin</h4>

            <div className="container">

                <form onSubmit={handleSubmit(submitForm)}>

                    <select value={day} {...register("dayCount")} onChange={handleSelect} className={"form-select"}>
                        <option value="1">1 kun</option>
                        <option value="7">1 hafta</option>
                        <option value="15">15 kun</option>
                        <option value="30">1 oy</option>
                    </select>

                    <button className={"btn btn-dark"}>save</button>
                </form>


            </div>
        </div>
    );
}

export default Settings;