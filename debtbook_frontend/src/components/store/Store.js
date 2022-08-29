import React, {useEffect, useState} from 'react';
import "./store.scss";
import {Pagination} from "antd";
import Rodal from "rodal";
import 'rodal/lib/rodal.css';
import img from "./downlaod.png";
import send from "./send.png";
import axios from "axios";
import {useLocation, useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";
import {toast} from 'react-toastify';


function Store(props) {

    const [v, setV] = useState(false);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [stores, setStores] = useState([]);
    const [search, setSearch] = useState("");
    const [search1, setSearch1] = useState("");
    const [storeId, setStoreId] = useState("");
    const [debtor, setDebtor] = useState(true);
    const [storeDebtor, setStoreDebtor] = useState([]);
    const [file, setFile] = useState("");
    const [totalElements, setTotalElements] = useState(0);


    const {
        register, handleSubmit, reset
    } = useForm();


    const navigate = useNavigate();

    const toggle = () => {
        setV(p => !p)
    }

    useEffect(() => {
        if (debtor) {
            getStores(page, search)
        } else {
            getMyStoreDebtors(0, storeId)
        }
    }, [])

    function getStores(page, search) {
        axios({
            url: "http://localhost:8080/api/admin/store",
            method: "get",
            params: {page, search},
            headers: {"Authorization": localStorage.getItem("token")}
        }).then(res => {
            setStores(res.data.content)
            setTotalElements(res.data.totalElements)
            setTotalPages(res.data.totalPages)
        })
    }

    function getMyStoreDebtors(storeId) {
        setDebtor(false)
        setStoreId(storeId)
        axios({
            url: "http://localhost:8080/admin/store-debtors",
            method: "get",
            params: {page: page, storeId},
            headers: {"Authorization": localStorage.getItem("token")}
        }).then(res => {
            setStoreDebtor(res.data.content)
            console.log(res.data.content)
        })
    }


    function paginationFunc(page) {
        setPage(page)
    }

    function handleSearch(e) {
        getStores(page, e.target.value)
        setPage(0);
        setSearch(e.target.value)
    }

    function handleSearchDebtor(e) {
        setSearch1(e.target.value);
    }


    function submitForm(item) {
        axios({
            url: "http://localhost:8080/api/admin/storemessage",
            method: "post",
            data: {attachmentId: file, ...item},
            headers: {"Authorization": localStorage.getItem("token")}
        }).then(res => {
            setV(false);
            reset({description: ""});
            setFile("");
            toast.success("Do'kon egalariga xabar yuborildi!")
        })
    }

    function handleFile(e) {

        let data = new FormData();
        data.append("file", e.target.files[0])

        axios({
            url: "http://localhost:8080/api/admin/store-image",
            method: "post",
            data,
            headers: {"Authorization": localStorage.getItem("token")}
        }).then(res => {
            setFile(res.data)
        })
    }

    return (
        <div className={"store"}>
            <h1 className={"text-center m-3"}>{debtor ? "Store" : "Store - debtors"}</h1>

            <div className="container d-flex">
                {
                    debtor ? <input
                        type="search"
                        className={"form-control"}
                        placeholder={"search ..."}
                        onChange={handleSearch}
                    /> : <input
                        type="search"
                        className={"form-control"}
                        placeholder={"search debtor ..."}
                        onChange={handleSearchDebtor}
                    />
                }
                <button className={"btn btn-dark"} onClick={toggle}>
                    send&nbsp;messsage
                </button>

                {
                    !debtor ? <button onClick={() => setDebtor(true)} className={"btn btn-dark"}>Store</button> : ""
                }
            </div>


            {
                debtor ? <div className="container">
                    <table className="table">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Owner</th>
                            <th>Phone number</th>
                            <th>storeName</th>
                            {/*<th>Debtors</th>*/}
                            <th> Sum debts</th>
                            <th>Address</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            stores.map((item, index) => <tr key={item.id}>
                                <td>{index + 1}</td>
                                <td>{item.fullName}</td>
                                <td>{item.phoneNumber}</td>
                                <td>{item.storeName}</td>
                                {/*<td>*/}
                                {/*    <div>*/}
                                {/*        <button*/}
                                {/*            onClick={() => getMyStoreDebtors(item.id)}*/}
                                {/*            className={"btn btn-dark"}*/}
                                {/*        >Debtors*/}
                                {/*        </button>*/}
                                {/*    </div>*/}
                                {/*</td>*/}
                                <td>{item.debt}</td>
                                <td><a target={"_blank"} href={item.storeLocation}><i
                                    className="text-danger fa-solid fa-location-dot"/></a></td>
                            </tr>)
                        }
                        </tbody>
                    </table>
                </div> : <div className={"container"}>
                    <table className="table">
                        <thead>
                        <tr>
                            <th>Debtor</th>
                            <th>PhoneNumber</th>
                            <th>Debt</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            storeDebtor.map((item, index) => <tr key={index}>
                                <td>{index + 1}</td>
                                <td>{item.fullName}</td>
                                <td>{item.phoneNumber}</td>
                                <td>{item.debt}</td>
                            </tr>)
                        }
                        </tbody>
                    </table>
                </div>
            }


            {
                totalPages>1? <div className="pagination">
                    <Pagination
                        showLessItems={true}
                        pageSize={7}
                        total={totalElements}
                        onChange={paginationFunc}
                    />
                </div>:""
            }

            <Rodal width={400} height={500} visible={v} onClose={() => toggle()}>
                <h5 className={"tex-center mt-3"}>Do'konlarga xabar yuborish</h5>

                <form onSubmit={handleSubmit(submitForm)}>
                    <label className={"file-label"}>
                        {
                            file ?
                                <img width={100} height={100} src={"http://localhost:8080/api/admin/store/img/" + file}
                                     alt="image"/> :
                                <img height={50} width={50} src={img} alt="download"/>
                        }
                        <input onChange={handleFile} accept={"image/*"} type="file"/>
                    </label>

                    <textarea {...register("description")} className={"form-control"} placeholder={"Xabar"}/>

                    <button className={"btn form-control"}>
                        <img height={30} width={30} src={send} alt=""/>
                    </button>

                </form>
            </Rodal>

        </div>
    );
}

export default Store;