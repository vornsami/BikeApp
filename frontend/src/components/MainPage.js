import React, { useEffect, useState }  from 'react'

import getPaths from './services/network'
import PathList from './PathList'

const MainPage = () => {
  const [paths, setPaths] = useState({})
  var sortBy = 'Distance'

  useEffect(() => {
    async function fetchData() {
      await setPaths(getPaths(sortBy, 50, 0))
    }
    try {
      fetchData()
    } catch (e) {
      console.error(e)
    }
  }, [sortBy])

  return <PathList paths={paths}/>
}

export default MainPage