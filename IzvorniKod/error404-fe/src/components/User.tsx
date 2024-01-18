import React from 'react';

function User(props){
    const {id, name, lastname, phone, timeOfArrival, placeOfArrival, sizePref, qualPref} = props.user

    return (
        <p>{firstname} {lastname} {phone} {timeOfArrival} {placeOfArrival} {sizePref} {qualPref} ({id})</p>
    );
} 

export default User;