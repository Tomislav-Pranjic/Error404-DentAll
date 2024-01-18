import React, { useState } from 'react';
import User from './User';
import './UserList.css';

function UserList(){

    const[users,setUsers] = useState<any[]>([]);

    React.useEffect(() => {
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