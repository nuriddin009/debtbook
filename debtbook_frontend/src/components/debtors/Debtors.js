import React, {useEffect, useState} from 'react';
import "./debtors.scss";
import {Pagination} from "antd";
import axios from "axios";
import {useParams, Outlet} from "react-router-dom";
import Rodal from "rodal";
import img from "../store/downlaod.png";
import send from "../store/send.png";
import {useForm} from "react-hook-form";
import {toast } from 'react-toastify';

function Debtors(props) {


    const [debtors, setDebtors] = useState([])
    const [v, setV] = useState(false);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [totalElements, setTotalElements] = useState(0);
    const [search, setSearch] = useState("");
    const [file, setFile] = useState("");


    const {
        register, handleSubmit, reset
    } = useForm();


    useEffect(() => {
        getDebtors(page, search)
    }, [])


    const getDebtors = (page, search) => {
        axios({
            url: "http://localhost:8080/api/admin/debtor",
            method: "get",
            params: {page, search},
            headers: {"Authorization": localStorage.getItem("token")}
        }).then(({data}) => {
            setDebtors(data.content)
            setTotalElements(data.totalElements)
            setTotalPages(data.totalPages)
        })
    }


    const toggle = () => {
        setV(p => !p)
    }

    function paginationFunc(page) {
        setPage(page-1)
        getDebtors(page-1,search)
    }


    function searchDebtors(e) {
        setPage(0);
        setSearch(e.target.value)
        getDebtors(page, e.target.value)
    }

    function handleFile(e) {
        let data = new FormData();
        data.append("file", e.target.files[0])

        axios({
            url: "http://localhost:8085/api/debtors/debtor-image",
            method: "post",
            data,
            headers: {"Authorization": localStorage.getItem("token")}
        }).then(res => {
            setFile(res.data)
        })
    }

    function submitForm(item) {
        axios({
            url: "http://localhost:8085/api/debtors/debtor-message",
            method: "post",
            data: {attachmentId: file, ...item},
            headers: {"Authorization": localStorage.getItem("token")}
        }).then(res=>{
            setV(false)
            setFile("")
            toast.success("Qarzdorlarga xabar yuborildi!")
        })
    }

    return (
        <div className={"debtors"}>
            <h1 className={"text-center m-3"}>Debtors</h1>

            <div className="container d-flex">
                <input
                    type="search"
                    className={"form-control"}
                    placeholder={"search ..."}
                    onChange={searchDebtors}
                />
                <button onClick={toggle} className={"btn btn-dark"}>
                    send&nbsp;message
                </button>
            </div>


            <div className="container">
                <table className="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Debtor</th>
                        <th>Phone number</th>
                        {/*<th>Stores</th>*/}
                        <th>sum debts</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        debtors.map((item, index) => <tr key={item.id}>
                            <td>{index + 1}</td>
                            <td>{item.fullName}</td>
                            <td>{item.phoneNumber}</td>
                            {/*<td>*/}
                            {/*    <div>*/}
                            {/*        /!*<button className="btn btn-dark">Stores</button>*!/*/}
                            {/*    </div>*/}
                            {/*</td>*/}
                            <td>{item.debt}</td>
                        </tr>)
                    }
                    </tbody>
                </table>
            </div>

            <div className="pagination">
                {
                    totalPages>1? <Pagination
                        defaultCurrent={20}
                        pageSize={10}
                        total={totalElements}
                        onChange={paginationFunc}
                    /> :""
                }
            </div>

            <Rodal width={400} height={500} visible={v} onClose={() => toggle()}>
                <h5 className={"tex-center mt-3"}>Qarzdorlarga xabar yuborish</h5>

                <form onSubmit={handleSubmit(submitForm)}>
                    <label className={"file-label"}>
                        {
                            file ? <img height={100} width={100} src={"http://localhost:8085/api/debtors/img/" + file}
                                        alt="image"/> :
                                <img height={50} width={50} src={img} alt="download"/>

                        }
                        <input onChange={handleFile} accept={"image/aces"} type="file"/>
                    </label>

                    <textarea
                        {...register("description")}
                        className={"form-control"}
                        placeholder={"Xabar"}
                    />

                    <button className={"btn form-control"}>
                        <img height={30} width={30} src={send} alt=""/>
                    </button>

                </form>
            </Rodal>

        </div>
    );
}

export default Debtors;