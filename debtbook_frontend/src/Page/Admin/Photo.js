import React from 'react';
import img from "./img.png";
import img1 from "./img_1.png";

function Photo(props) {
    return (
        <div>
            <img src={img1} alt="img1"/>
            <img src={img} alt="img"/>
        </div>
    );
}

export default Photo;