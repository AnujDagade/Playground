import { useState } from 'react'

import './App.css'

function App() {


  return (
    <>
      <div> 
        <form >
          <label htmlFor="file ">Upload</label>
          <input type='file' id='file' multiple />
        </form>
      </div>
    </>
  )
}

export default App
