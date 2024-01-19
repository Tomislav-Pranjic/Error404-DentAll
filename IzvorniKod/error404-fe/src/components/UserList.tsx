import React, { useState } from 'react';
import User from './User';
import './UserList.css';
import { Base64 } from 'js-base64';

function UserList(){

    const[users,setUsers] = useState<any[]>([]);

    const storedUsername = localStorage.getItem('username');
    const storedPassword = localStorage.getItem('password');

    React.useEffect(() => {
        const options = {
            method: 'GET',
            headers: new Headers({
                "Authorization": `Basic ${Base64.encode(`${storedUsername}:${storedPassword}`)}`,
                "Content-Type": "application/json",
                  "Accept": "*/*",
                "Accept-Encoding": "gzip, deflate, br",
                "Connection": "keep-alive"
            }),
            body: ''
        };
        fetch('/users')
        .then(data => data.json())
        .then(users => setUsers(users))
    }, []);
    
    return(
        <div className='UserList'>
            {
                users.map(user => 
                    <User key={user.id} user={user}/>
                    )
            }
        </div>
    );
}

export default UserList;