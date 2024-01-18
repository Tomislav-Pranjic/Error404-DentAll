import React from 'react';

function User(props: { user: { id: any; firstname: any; lastname: any; phone: any; accTypePrefId: any; dateOfBirth: any; }; }){
    const {id, firstname, lastname, phone,accTypePrefId, dateOfBirth } = props.user

    return (
        <p>{firstname} {lastname} {phone} {accTypePrefId} {dateOfBirth} </p>
    );
} 

export default User;

