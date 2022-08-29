import React, {useEffect, useState} from 'react';
import "./App.scss";
import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import Login from "./Page/Login/Login";
import Admin from "./Page/Admin/Admin";
import Photo from "./Page/Admin/Photo";
import Store from "./components/store/Store";
import Debtors from "./components/debtors/Debtors";
import Settings from "./components/settings/Settings";
import Error from "./Page/not-found/404";
import MessageBox from "./components/box/Message-box";
import axios from "axios";

function App(props) {

    const [roles, setRoles] = useState([]);
    const [token, setToken] = useState("");
    const location = useLocation();
    let navigate = useNavigate();

    useEffect(() => {
        getMyRoles()
        getMe()
        adminPage()
    }, [location.pathname])


    const blockedPages = [
        {url: "/admin", roles: ["ROLE_ADMIN"]},
        {url: "/admin/stores", roles: ["ROLE_ADMIN"]},
        {url: "/admin/debtors", roles: ["ROLE_ADMIN"]},
        {url: "/admin/settings", roles: ["ROLE_ADMIN"]},
        {url: "/admin/message-box", roles: ["ROLE_ADMIN"]}
    ]

    function getMyRoles() {
        axios({
            url: "http://localhost:8080/api/me",
            method: "get",
            headers: {"Authorization": localStorage.getItem("token")}
        }).then(res => {
            setRoles(res.data.roles);
        })
    }

    function isBlockedPage() {
        let a = false;
        blockedPages.map((item) => {
            if (item.url === location.pathname) {
                a = true;
            }
        })
        return a;
    }


    function hasRole(roles) {
        let currentPage = blockedPages.filter(item => item.url === location.pathname)[0]
        currentPage.roles.map(item => {
            if (roles.filter(fitem=>fitem.authority===item).length>0) {
                return true
            }
        })
        return false;
    }


    function getMe() {
        // let token = localStorage.getItem("token");
        if (isBlockedPage() && hasRole(roles)) {
            navigate("/admin/stores")
        } else {
            // navigate("/")
        }
    }

    function adminPage() {
        const pathname = location.pathname;
      let token=   localStorage.getItem("token");
        if (pathname.startsWith("/admin") &&  token!== null && token.length>100) {}else {
            navigate("/")
        }

    }


    return (
        <div className={"App"}>

            <Routes>

                <Route path={"/"} element={<Login/>}/>
                <Route path={"/admin"} element={<Admin/>}>
                    <Route path={"/admin/stores"} element={<Store/>}/>
                    <Route path={"/admin/messages-box"} element={<MessageBox/>}/>
                    <Route path={"/admin/debtors"} element={<Debtors/>}/>
                    <Route path={"/admin/settings"} element={<Settings/>}/>
                </Route>
                <Route path={"/img.png"} element={<Photo/>}/>
                <Route path={"404"} element={<Error/>}/>
            </Routes>

        </div>
    );
}

export default App;