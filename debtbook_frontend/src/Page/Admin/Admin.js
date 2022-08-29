import React, {useEffect, useState} from 'react';
import "./admin.scss";
import {Link, Outlet, useLocation} from "react-router-dom";

function Admin(props) {

    const {pathname} = useLocation();


    useEffect(() => {
        document.title = "Admin page"
    }, [])

    const [bars, setBars] = useState(false);

    function getBg() {

    }


    return (
        <div className={"admin"}>
            <div className={`menus ${bars ? "active-bar" : "toggle"}`}>

                <div className="toggle-btn">
                    {
                        bars ? <i
                            onClick={(p) => setBars(p => !p)}
                            className="fa-solid fa-bars text-primary"
                        /> : <i
                            onClick={(p) => setBars(p => !p)}
                            className="fa-solid fa-square-xmark text-danger"
                        />
                    }
                </div>


                <ul className={"list-group"}>
                    <Link style={{textDecoration: "none"}} to={"/admin/stores"}>
                        <li
                            className={`list-group-item ${pathname === "/admin/stores" ? "bg-primary text-white" : ""}`}
                        >
                            <i className="fa-solid fa-store"/> {!bars ? "Do'konlar" : ""}
                        </li>
                    </Link>

                    <Link style={{textDecoration: "none"}} to={"/admin/debtors"}>
                        <li
                            className={`list-group-item ${pathname === "/admin/debtors" ? "bg-primary text-white" : ""}`}
                        >
                            <i className="fa-solid fa-users"/> {!bars ? "Mijozlar" : ""}
                        </li>
                    </Link>
                    <Link style={{textDecoration: "none"}} to={"/admin/settings"}>
                        <li
                            className={`list-group-item ${pathname === "/admin/settings" ? "bg-primary text-white" : ""}`}
                        >
                            <i className="fa-solid fa-gear"/> {!bars ? "Sozlamalar" : ""}
                        </li>
                    </Link>
                    {/*<Link style={{textDecoration: "none"}} to={"/admin/messages-box"}>*/}
                    {/*    <li*/}
                    {/*        className={`list-group-item ${pathname === "/admin/messages-box" ? "bg-danger text-white" : "text-danger"}`}*/}
                    {/*    >*/}
                    {/*        <i className="fa-solid fa-inbox "/> {!bars ? "Messages" : ""}*/}
                    {/*    </li>*/}
                    {/*</Link>*/}
                </ul>
            </div>
            <div className="content">
                <Outlet/>

            </div>

        </div>
    );
}

export default Admin;