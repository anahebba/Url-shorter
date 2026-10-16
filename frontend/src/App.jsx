import { useState,useEffect } from 'react'
import './App.css'

function App() {
  const [longUrl, setLongUrl] = useState('')
  const [shortUrl, setShortUrl] = useState('')
  const [links, setLinks] = useState([])


  const handleShorten = async () => {
  const response = await fetch('http://localhost:8080/api/shorten', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ url: longUrl }),
  })
  const data = await response.json()
  setShortUrl('http://localhost:8080/' + data.shortCode)
  loadLinks()
}

const loadLinks = async () => {
  const response = await fetch('http://localhost:8080/api/urls')
  const data = await response.json()
  setLinks(data)
}



useEffect(() => {
  loadLinks()
}, [])

  return (
    <div>
      <h1>URL Shortener</h1>

      <input
        type="text"
        placeholder="Paste your long URL here"
        value={longUrl}
        onChange={(e) => setLongUrl(e.target.value)}
      />

      <button onClick={handleShorten}>Shorten</button>
      {shortUrl && <a href={shortUrl} target="_blank">{shortUrl}</a>}


     <h2>Recent links</h2>
<table>
  <tbody>
    {links.map((link) => (
      <tr key={link.id}>
        <td>
          <a href={'http://localhost:8080/' + link.shortCode} target="_blank">
            {link.shortCode}
          </a>
        </td>
        <td>{link.longUrl}</td>
      </tr>
    ))}
  </tbody>
</table>
     
    </div>
  )
}

export default App