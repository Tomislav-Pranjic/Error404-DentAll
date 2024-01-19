import { FormEvent, useState } from 'react';


function UserForm() {

    const [userForm, setUserForm] = useState({ firstName: '', lastName: '', email: '', phoneNumber: '', accTypePrefId: '', dateOfBirth: ''});

    function onChange(event: { target: { name: any; value: any; }; }) {
        const {name, value} = event.target;
        setUserForm(oldForm => ({...oldForm, [name]: value}))
    }


    function onSubmit(e: { preventDefault: () => void; }) {
        e.preventDefault();

        const data = {

            firstName: userForm.firstName,
            lastName: userForm.lastName,
            email: userForm.email,
            phoneNumber: userForm.phoneNumber,
            accTypePrefId: userForm.accTypePrefId,
            dateOfBirth: userForm.dateOfBirth

        };
        const options = {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(data)
        };

        return fetch('/api/user', options);
    }

    
    return (
        <div className="UserForm">
            <h2>New User</h2>
            <form onSubmit={onSubmit}>
                <div className="FormRow">
                    <label>First name</label>
                    <input name='firstname' onChange={onChange} value={userForm.firstName}/>
                </div>
                <div className="FormRow">
                    <label>Last name</label>
                    <input name='lastname' onChange={onChange} value={userForm.lastName}/>
                </div>
                <div className="FormRow">
                    <label>Email</label>
                    <input name='email' type='email' onChange={onChange} value={userForm.email}/>
                </div>
                <div className="FormRow">
                    <label>Phone Number</label>
                    <input name='phoneNumber' type='tel' onChange={onChange} value={userForm.phoneNumber}/>
                </div>
                <div className="FormRow">
                    <label>Preferred Type of Accommodation</label>
                    <input name='accTypePrefId' onChange={onChange} value={userForm.accTypePrefId}/>
                </div>
                <div className="FormRow">
                    <label>Date Of Birth</label>
                    <input name='dateOfBirth' type='date' onChange={onChange} value={userForm.dateOfBirth}/>
                </div>
                <button type="submit">Add User</button>
            </form>
        </div>
    )
}

export default UserForm;